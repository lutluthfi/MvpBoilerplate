# MvpBoilerplate

This is personal boilerplate. Maybe I'll think to renew for anyone who needs

# How to use

Add this code to your build.gradle project allprojects block.

for example:

    allprojects {
      repositories {
          ...
          maven { url 'https://jitpack.io' }
      }
    }
    
Add implementation 'com.github.lutluthfi:MvpBoilerplate:x.x.x' to your build.gradle app dependencies block.

for example:

    dependencies {
      implementation 'com.github.lutluthfi:MvpBoilerplate:x.x.x'
    }
