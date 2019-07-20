-keep class android.support.v7.widget.searchView{ *; }
-keep class android.support.v7.widget.ShareActionProvider{ *; }
# iText/droidText/PDF stuff
-keep class com.lowagie.text.** { *; }
# iTextAsian
-keep class com.itextpdf.** { *; }
# Fix for Samsung Galaxy Lite 4.2.2 bug: https://desc.google.com/p/android/issues/detail?id=78377
-keep class android.support.v7.widget.** { *; }
-keep interface android.support.v7.widget.** { *; }

# Restoring a stack trace with line numbers
# -renamesourcefileattribute ProGuard (adb: turned this off to improve Fabric issue where "Proguard" shows up)
-keepattributes SourceFile,LineNumberTable

# For PlugPDF and its dependencies
-keep class com.epapyrus.plugpdf.core.** { *; }
-dontwarn fi.harism.**

# For Parse
-dontwarn com.facebook.**
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.**
-dontwarn okio.**

# For OpenCSV
-dontwarn com.opencsv.**

# Universal image loader
-keep class com.nostra13.** { *; }
-keepclassmembers class com.nostra13.** { *; }

# Keep field names for our DAO objects so we can serialize up to Parse.com nicely
-keepclassmembers class com.aadhk.woinvoice.bean.** { private <fields>; }

# Fix cause I ripped out org.bouncycastle from DroidText.jar and com.lowagie.text.pdf.fonts.cmaps
-dontwarn com.lowagie.text.pdf.fonts.cmaps.**
-dontwarn repack.org.**

# Card.io
-keep class io.card.**
-keepclassmembers class io.card.** { *; }
# Uncomment these when DEBUGGING
#-dontobfuscate
#-optimizations !desc/simplification/arithmetic,!field/*,!class/merging/*,!desc/allocation/variable

# For Crashlytics to work around their bug where they don't deobfuscate exceptions
-keep public class * extends java.lang.Exception
-dontwarn io.card.**
#OKhttp RULES START
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
#OKhttp RULES END

#picasso rules START
-dontwarn com.squareup.okhttp.**
#picasso rules END
-keepattributes EnclosingMethod
-ignorewarnings

-keep class * {
    public private *;
}
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
