object AndroidConfig {
    const val applicationId: String = "com.abdulaziz.tala"
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val minSdk = 24
    const val targetSdk = 32
    const val compileSdk = 32
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode = 100

    const val androidTestInstrumentationRunner = "com.abdulaziz.tala.utils.TalaAssignmentRunner"
}
