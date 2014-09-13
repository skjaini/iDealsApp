Deals App - Android
=========

# Setup

## Java

* Install Java 7 if you dont have it already. You can download it from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* Install the whole JDT, Java Development Kit, not just the JRE.

## Eclipse

* Download the ADT Bundle [here](http://developer.android.com/sdk/index.html)
* Unzip the downloaded zip file.
* Preferably, move it to the Desktop folder.
* Launch eclipse within this folder.

## Android Tools

* Launch Eclipse, and open Window->Android SDK Manager.
* Select the following and install them
  1. Android SDK Tools
  2. Android SDK Platform Tools
  3. Android 4.4 (API 19) and Android 4.3 (API 18) both.
  4. Android SUpport Library
  5. Google Play Services
  6. Google Play for Froyo (may not be absolutely necessary)
  7. Intel x86 HAXM
  
## INTEL X86 HAXM

* Download and install the Intel HAXM Emulator Installer.
* While installing it, allocate atleast 512 MB of memory for it.

## Genymotion

Genymotion is a fast, memory-efficient virtual machine that runs the Android OS and emulates an Android device more similar than the official emulator that comes with Eclipse.

### Installation
* Register in the [Genymotion website](https://cloud.genymotion.com/page/customer/login/?next=/)
* Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads) if you dont have it installed.
* Download the [Genymotion Emulator](https://cloud.genymotion.com/page/launchpad/download/).
* Install the setup file:
  1. For Windows, runs the MSI installer.
  2. For Mac, open the .dmg file.
  
### Eclipse Genymotion plugin
* Open Eclipse, go to Help/Install new software.
* Add a new software to get updates. Name: Genymotion, URL: http://plugins.genymotion.com/eclipse.
* Check all, accept licenses, terms and conditions and install it.
* Restart eclipse to see the Genymotion Eclipse plugin.

### Running genymotion
* Open Genymotion application, sign in and add a new device Nexus 4 - 4.3 - API 18.
* In Eclipse, click the genymotion icon and start the new device.

## Setup Google Play Services

Open Eclipse => Windows => Android SDK Manager and check whether you have already downloaded "Google Play services" or not under Extras section. If not, select "Google Play services" and install the package.

After downloading the play services we need to import the project to Eclipse which will be used as a library for our maps project.

1. In Eclipse goto File ⇒ Import ⇒ Android ⇒ Existing Android Code Into Workspace

2. Click on Browse and select "Google Play Services" project from your android sdk folder. You can locate the play services library project from <your-android-sdk-path>\extras\google\google_play_services\libproject\google-play-services_lib

3. Be sure to check "Copy projects into workspace" option as shown in the below image.
4. Right click on project and select "Properties". In the Properties window on left side select "Android". On the right you can see a Add button under library section. Click it and select google play services project which we imported previously.
