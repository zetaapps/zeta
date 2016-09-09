# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Manjunath/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
#class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {   public *;}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Add any project specific keep options here:
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontoptimize
-verbose

### standard android settings
-keepattributes *Annotation*,EnclosingMethod,Signature,SourceFile,LineNumberTable

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# Needed for AppCompat SearchView
-keep public class android.support.v7.widget.SearchView {
   public <init>(android.content.Context);
   public <init>(android.content.Context, android.util.AttributeSet);
}

# TODO: Add the following if encountering other support-library issues
#
#-keep class android.support.v7.widget.** { *; }
#-keep interface android.support.v7.widget.** { *; }

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**
-dontwarn org.fest.**
-dontwarn sun.misc.Unsafe
### end standard android exclusions

### settings for google play services, see: http://developer.android.com/google/play-services/setup.html
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

# Goofy
-keepnames @com.google.android.gms.common.annotation.KeepName class *

-keepnames class * extends android.support.v4.app.Fragment

-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

### ZETA RELATED

# BuildConfig
-keep class zeta.android.apps.BuildConfig

# keep any classes with view/layout initialization methods
-keepclasseswithmembers class zeta.android.apps.** {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class zeta.android.apps.** {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class zeta.android.apps.** {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# keep any classes to by pass obfuscation of enums if any
-keepclassmembers class zeta.android.apps.** {
    <fields>;
}

### Library-specific settings
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**

# JAVAX annotation
-dontwarn javax.annotation.**

# okhttp settings
-dontwarn com.squareup.okhttp.**

# Prettytime settings
-keep class org.ocpsoft.prettytime.i18n.**
-keepnames class ** implements org.ocpsoft.prettytime.TimeUnit

# ORMLite settings
# OrmLite uses reflection
-keep class com.j256.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }

#ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# EventBus
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep class org.greenrobot.** { *; }

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keepnames class com.target.ui.appconfig.GlideConfigModule

#Retrofit 2.0
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#Square libraries
-dontwarn com.squareup.haha.guava.collect.**
-dontwarn com.squareup.haha.perflib.io.**
-dontwarn com.squareup.haha.trove.THashMap**

#Retrolambda
-dontwarn java.lang.invoke.*

#RxJava
-dontwarn rx.internal.util.unsafe.**
-keep class rx.internal.util.unsafe.** {
    *;
 }

 #Autovalue
 -dontwarn autovalue.shaded.com.**
 -dontwarn com.google.auto.value.**

#Mockito
-dontwarn org.mockito.**

