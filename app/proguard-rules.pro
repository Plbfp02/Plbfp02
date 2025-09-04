# Keep Kotlin metadata
-keepclassmembers class kotlin.Metadata { *; }
# Room schema
-keep class androidx.room.** { *; }
-dontwarn javax.annotation.**