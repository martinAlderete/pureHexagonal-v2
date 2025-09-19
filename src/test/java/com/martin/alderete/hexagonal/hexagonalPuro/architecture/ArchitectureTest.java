package com.martin.alderete.hexagonal.hexagonalPuro.architecture;

import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/*
 * esta clase define las reglas de arquitecture,lo permitido y no lo permitido
 * esto es lo que va defender la arquitectura de cualquier uso indebido
 * */
@AnalyzeClasses(packages = "com.gyl.bys")
public class ArchitectureTest {

    //----------------------------  Condición personalizada   ----------------------------


    private static final ArchCondition<JavaClass> usarSoloAnotacionesDeJava =
            new ArchCondition<>("usar solo anotaciones de java.*, javax.* o com.gyl.bys") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    // Anotaciones en la clase
                    checkAnnotations(item, item.getAnnotations(), "clase", events);

                    // Anotaciones en campos
                    item.getFields().forEach(field ->
                            checkAnnotations(item, field.getAnnotations(),
                                    "campo " + field.getName(), events)
                    );

                    // Anotaciones en métodos y sus parámetros
                    item.getMethods().forEach(method -> {
                        checkAnnotations(item, method.getAnnotations(),
                                "metodo " + method.getName(), events);

                        method.getParameters().forEach(param ->
                                checkAnnotations(item, param.getAnnotations(),
                                        "parametro del método " + method.getName(), events)
                        );
                    });
                }

                private void checkAnnotations(JavaClass owner,
                                              Set<? extends JavaAnnotation<?>> annotations,
                                              String target,
                                              ConditionEvents events) {
                    for (JavaAnnotation<?> annotation : annotations) {
                        String name = annotation.getRawType().getFullName();
                        if (!(name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("com.gyl.bys"))) {
                            String message = String.format(
                                    "La clase %s usa una anotación no permitida en %s: %s",
                                    owner.getFullName(), target, name
                            );
                            events.add(SimpleConditionEvent.violated(owner, message));
                        }
                    }
                }
            };

    //----------------------------  Domain   ----------------------------

    @ArchTest
    public static final ArchRule domain_no_depende_de_application_o_infrastructure =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..application..", "..infrastructure..");

    @ArchTest
    public static final ArchRule domain_solo_puede_usar_anotaciones_java =
            ArchRuleDefinition.classes().that().resideInAPackage("..domain..")
                    .should(usarSoloAnotacionesDeJava);

    //----------------------------  Application   ----------------------------

    @ArchTest
    public static final ArchRule application_no_puede_depender_de_infrastructure =
            noClasses().that().resideInAPackage("..application..")
                    .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    public static final ArchRule application_solo_puede_usar_anotaciones_java =
            ArchRuleDefinition.classes().that().resideInAPackage("..application..")
                    .should(usarSoloAnotacionesDeJava);
}