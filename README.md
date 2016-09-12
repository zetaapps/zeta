# Zeta Bootstrap (Work In Pogress)

# ![Zeta](https://cloud.githubusercontent.com/assets/1502341/17840452/5f574d84-67cd-11e6-83a5-9abb590f399f.png?raw=true "Zeta Banner")

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
![SDK](https://img.shields.io/badge/SDK-16%2B-green.svg)
![Release](https://img.shields.io/badge/release-v1.0-green.svg)
[![Build Status](https://travis-ci.org/zetaapps/zeta.svg?branch=master)](https://travis-ci.org/zetaapps/zeta)
[![CircleCI](https://circleci.com/gh/zetaapps/zeta.svg?style=svg)](https://circleci.com/gh/zetaapps/zeta)
[![codecov](https://codecov.io/gh/zetaapps/zeta/branch/master/graph/badge.svg)](https://codecov.io/gh/zetaapps/zeta)

<a href="https://play.google.com/store/apps/details?id=zeta.andriod.apps.free">
<img align="middle" alt="Get it on Google Play" src="https://cloud.githubusercontent.com/assets/1502341/17841713/0670bc32-67e1-11e6-907e-2f850d755b8f.png" />
</a>

***What is project Zeta?***

A template / bootstrap application that includes tons of great open source tools and frameworks. Goal here is to reduce the boilerplate code and help build clean architecture at the same time. Zeta's design principles can be applied for any fresh or existing project. Zeta covers all the nitty gritty details out of the box, with quality and performance in mind along with reducing development time to put up the common design patterns in place.

**Project Zeta Principles**

`#Performance Matters` 

`#Quality Matters` 

`#Testing Matters (TDD)` 

####What needs to done from your end to start with? 

1. Just drop your fragments in place and follow some of the design patterns. That's it!

for eg: All the fragments has to be derived from `BaseNavigationFragment` which provides some common functionalities like having navigation manager ready, and infamous `onSavedInstanceCalled()` checks. Along with this all fragments will be dagger aware. Which means all the heavy lifting of the dagger pattern is provided for you *free* out of the box.

`YourFragment extends BaseNavigationFragment implements YourPresentation`

####Design Patterns

1. MVP Pattern (Makes easier for unit testing the business / app logic)
2. Dependencies Injection Pattern
3. mViews Pattern (helps to consolidates all the views in inner class for easier access which avoids multiple null checks)
4. Factory Pattern
5. Singleton Pattern
6. Composite Pattern
7. Builder Pattern
8. Adapter Pattern etc.

*What is `mViews` pattern?* Well, It's a collection of all views in one place (Couldn't think of any better name for this pattern, So let's call it `mViews` for now)

```
private Views mViews;

static class Views extends BaseViews {

        @Bind(R.id.zeta_progress_bar)
        ProgressBar progressBar;

        @Bind(R.id.zeta_list_view)
        RecyclerView listView;

        Views(View root) {
            super(root);
        }
    }
```

This is a static inner class grouped with all views binding at one place. `Views` class are typically derived from `BaseViews` which is a basically butterknife aware base class. `BaseViews` provides binding and cleaning up of the all the views for *free*. What it means to us is, we don't need to call `ButterKnife.bind(this, root);` every time in your fragments. Yay! (DRY principle).

To clear all the views just make sure to call these two lines on your ` public void onDestroyView()` thats' it!

```
mViews.clear();
mViews = null;
``` 

####What features we get out of project zeta?

1. Static code analysis (#Quality Matters)
    *      [PMD](https://pmd.github.io/)
    *      [FindBugs](http://findbugs.sourceforge.net/)
    *      [Android Lint](http://tools.android.com/tips/lint)
    *      [Checkstyle](http://checkstyle.sourceforge.net/)
    
2. Developer Settings where you can enable/disable. (#Testing Matters)
    *      [Stetho](http://facebook.github.io/stetho/)
    *      [LeakCanary](https://github.com/square/leakcanary)
    *      [TinyDancer](https://github.com/brianPlummer/TinyDancer) 
    *      [Lynx](https://github.com/pedrovgs/Lynx)
    *      [Strict Mode](https://developer.android.com/reference/android/os/StrictMode.html)
    *      [OkHttp Logging](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

3. External libraries. (#Performance Matters)
    *      [Glide](https://github.com/bumptech/glide)
    *      [OkIO](https://github.com/square/okio)
    *      [OkHttp](https://github.com/square/okhttp)
    *      [RxJava](https://github.com/ReactiveX/RxJava)
    *      [RxAndroid](https://github.com/ReactiveX/RxAndroid)
    *      [Retrofit](https://github.com/square/retrofit)
    *      [Dagger](https://github.com/google/dagger)

###Tests

####Unit tests

- API unit tests are covered at `FlickrApiTest`
- Model Later unit test are covered at `FlickrModelTransformerTest`
- MVP's presenter unit test are covered at `HomePresenterTest`
- Debug builds unit test are covered at `DebugPresenterTest` 

####Integration tests

WIP (Work In Progress)

####Functional (UI) tests

WIP (Work In Progress)

####Peformance tests

WIP (Work In Progress)

---

## Build 
To build the project just run `sh ci.sh` (yep, that easy, because it should be easy).
Project Zeta comes with three different build variants plus with two different flavor. 

**Build variants**

1. Debug
2. Release 
3. Automation

**Build flavor**

1. Free
2. Paid 

*Why we need so many build variations?*

1. Automation builds can be pointed to mock data's so that it can be run against automated scripts.
2. Debug build for development purpose ofcourse.
3. Release build for obvious reasons. (Production deployment)

*Why we need build flavor?*

Actually we don't. This is just to show case that we still can many flavor of builds with these settings. So out of the box we have ability to check against the build flavor and show or hide features.

All these build can coexist in the same devices as these builds comes with it's own build variant package suffix like `zeta.andriod.apps.automation.free` `zeta.andriod.apps.automation.paid` `zeta.andriod.apps.debug.free` & `zeta.andriod.apps.debug.paid` - Woahzzza! That's sweet. Isn't it.

# ![Zeta](https://cloud.githubusercontent.com/assets/1502341/17843715/dab97154-67f6-11e6-9ad4-c39fded761d4.png "Zeta Apps Versions")

- Release build
- (A) Automation build 
- (D) Debug build

As a bonus. Project Zeta comes with continous integration capabilities 

1. Integration with [Travis](https://travis-ci.org/) `.travis.yml`
2. Integration of the [Circle CI](https://circleci.com/) `circle.yml` 

Automate all the builds Yay!

LICENCE
-----

Zeta by [Manjunath Chandrashekar](https://www.linkedin.com/in/manjunath-chandrashekar), [Manij Shrestha](https://www.linkedin.com/in/manijshrestha), [Collin Flynn](https://www.linkedin.com/in/collin-flynn-32221233) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

    Copyright 2016 Manjunath Chandrashekar, Manij Shrestha, Collin Flynn.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-----


