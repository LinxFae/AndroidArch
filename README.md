# AndroidArch
[![Build Status](https://travis-ci.org/ir2pid/AndroidArch.svg?branch=master)](https://travis-ci.org/ir2pid/AndroidArch)
[![codebeat badge](https://codebeat.co/badges/68d49e3e-f69b-4b7f-b2ef-677ee44eb879)](https://codebeat.co/projects/github-com-ir2pid-androidarch-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f5de87278efb4fa69d55ab3be7ba36a1)](https://www.codacy.com/app/ir2pid/AndroidArch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ir2pid/AndroidArch&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/ir2pid/AndroidArch/branch/master/graph/badge.svg)](https://codecov.io/gh/ir2pid/AndroidArch)
[![GitHub last commit](https://img.shields.io/github/last-commit/ir2pid/AndroidArch.svg)](https://github.com/ir2pid/AndroidArch)
</br><!--
[![shields](https://img.shields.io/badge/minSdkVersion-15-yellowgreen.svg)](https://github.com/ir2pid/AndroidArch)
[![shields](https://img.shields.io/badge/targetSdkVersion-26-orange.svg)](https://github.com/ir2pid/AndroidArch)
[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=true)](https://github.com/ir2pid/AndroidArch)
-->
</br>

A proof of concept for google's architecture components which uses REST api from randomuser.me/
to fetch users and display in a master detail view

coded in a mixture of kotlin and Java uses android architecture components like
- Uses MVVM architecture with data binding
- LiveData for observable data source,
- ViewModel for Activity/Fragment lifecycle awareness
- Room for ORM
- okHttp and Retrofit for network calls
- RxJava for observing network data and applying filters on stream
- Dagger2 for dependency injection

### For unit testing, integration and UI testing.
- Junit,
- Mockito and
- Espresso

### For CI and static code analysis 
- Travis
- CodeBeat
- Codacy
- Codecov
