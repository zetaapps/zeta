# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Manjunath/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# keep any classes to by pass obfuscation of enums
-keepclassmembers class zeta.apps.flickr.** {
    <fields>;
}

#Flickr API's
-keepclassmembers class zeta.apps.flickr.api.** { *; }

# BuildConfig
-keep class zeta.apps.flickr.BuildConfig

