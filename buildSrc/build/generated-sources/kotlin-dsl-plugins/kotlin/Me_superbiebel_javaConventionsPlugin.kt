/**
 * Precompiled [me.superbiebel.java-conventions.gradle.kts][Me_superbiebel_java_conventions_gradle] script plugin.
 *
 * @see Me_superbiebel_java_conventions_gradle
 */
class Me_superbiebel_javaConventionsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Me_superbiebel_java_conventions_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
