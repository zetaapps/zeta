# Zeta Bootstrap

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

###What is project Zeta?

Zeta is a bootstrap Android application.
Zeta stands ready to take your project off the ground quickly, without sacrificing flexible architecture or incurring technical debt by its absence.  

### What does Zeta do for me?

Starting a blank Android project carries many _hidden costs_.
You may have tried creating a new project only to find you still need to ...
- Include state of the art libraries
- Configure multiple build types (testing, dev, prod)
- Set up common dependency injection scopes, modules, components
- Create base classes for activities, fragments, presenters
- Wire up intelligent logging that is enabled in development but deactivates in production.
- Configure Pro-Guard all over again

In the worst case, you even delayed some of the above as technical debt.
Zeta comes with these (and more) pre-configured, so you can start writing _your_ code, _now_.

With Zeta there's no reason to do without common good practices, even in your prototypes.

###What do I need to do to start using Zeta?

In three words: **fork and go**.

Fork the Zeta project, and change the package name.  

Zeta has baked in some conservative choices about architecture and design patterns, while trying not to impose too many opinions about how to shape your application.
You'll find familiar concepts, configured by experienced developers that have been shipping stable Android applications to millions of active users for years.

**Patterns Like**

1. MVP
2. Dependencies Injection via Dagger 2
3. Retrofit and OkHttp, backed by injected `OkHttpClient` (and thereby easily configurable)
4. Rx `Observable`, and related custom handlers for easy integration into new `Fragment`s and `Activities`
5. `AutoValue` for safe data types
6. Logging that never touches production
7. Common `Android` gotchas, pre-caught and intelligently handled.

###That's nice, What else?

Other features that are are often skipped by rushed developers:
* How about _Static code analysis?_

    -  [PMD](https://pmd.github.io/)
    -  [FindBugs](http://findbugs.sourceforge.net/)
    -  [Android Lint](http://tools.android.com/tips/lint)
    -  [Checkstyle](http://checkstyle.sourceforge.net/)
    
* And some _Developer Quality-Of-Life settings?_

    -  [Stetho](http://facebook.github.io/stetho/)
    -  [LeakCanary](https://github.com/square/leakcanary)
    -  [TinyDancer](https://github.com/brianPlummer/TinyDancer) 
    -  [Lynx](https://github.com/pedrovgs/Lynx)
    -  [Strict Mode](https://developer.android.com/reference/android/os/StrictMode.html)
    -  [OkHttp Logging](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

---

###Build
(`cmd + f9`)
Project Zeta comes with three different build variants plus with two different flavor.

**Build variants**

1. Debug
2. Release 
3. Automation

**Build flavor**

1. Free
2. Paid 

You will probably want to change these defaults to align with your project's needs, but take note that automation builds are set up so that you can point to mock data.  This is useful for running automated test scripts without invoking a real data layer.
The `Debug` variant comes configured with logging enabled, and is typically what you'll be building as you develop on your local machine.

You may not need the build flavors `Free` and `Paid`, but observe that Zeta is prepared to check against the current build flavor and respond by enabling or disabling any features you choose.

Also, all these build can *coexist* in the same device.

# ![Zeta](https://cloud.githubusercontent.com/assets/1502341/17843715/dab97154-67f6-11e6-9ad4-c39fded761d4.png "Zeta Apps Versions")

- Release build
- (A) Automation build 
- (D) Debug build

Oh, and don't forget Continuous Integration:

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


