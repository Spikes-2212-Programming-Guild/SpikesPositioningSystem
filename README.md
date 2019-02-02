# SpikesPositioningSystem
<b><ul><li><i><a><strong>The calculate method of the OdometryHandler class should be called repeatedly, otherwise your robot's position
would not be recalculated, and therefore will be substantially inaccurate.
</b></ul></li></i></a></strong>

This is the Spikes Positioning System or SPS.

The Spikes Positioning System is a way to calculate your robot's position using two distance sensors and an angle sensor.
This program was written by the FRC team The Spikes #2212. For more information about us, visit <a href='http://www.spikes2212.com/'> our site</a>.

* ####  <a href='#Purpose'>What Is the purpose of the Spikes Positioning System?</a>
* ####  <a href='#Download&Setup'>How can I install SPS?</a>
* ####  <a href='#More'>How does it work?</a>

## <a name = 'Purpose'> The Purpose Of The Program
 
Many times during an FRC match the drivers have trouble seeing the robot and navigating the field, wasting precious time. The purpose of SPS is to provide the drivers with accurate informaion of the robot's whereabouts during the match and creating an easy way to calculate and use the robot's location in your code.

## <a name = 'Download&Setup'> Download and Setup SPS </a>

### Using SPS in Gradle Projects
#### Using A Local Jar

download the library (.zip file) from <a href='https://github.com/Spikes-2212-Programming-Guild/SpikesPositioningSystem/releases'> the latest release </a> and you can download the jar there.
1. add a `libs` directory to your project root.
2. Extract the SPS.zip to that folder
3. in your `gradle.build` go to the `dependencies` section.
once there, add the following line `compile files('libs/YOUR_JAR.jar')` under all the other dependencies.

### Using SPS in Eclipse Projects
1. download the .jar file using the method mentioned above into C:\Users\%USERNAME%\wpilib\user\java\lib
2. Open Eclipse
3. Right click on the wanted project
4. Hover on Build Path
5. Click on Configure Build Path
6. Click on Add External JARs
7. Find and add the SpikesLib jar file
8. return 0

## <a name = 'More'> Learn more </a>

### links to articles
<li>The main article - https://pdfs.semanticscholar.org/67b0/b4fe8972762bc8d86066cfeb427a9a7b3d58.pdf</li>
<li>An article about Kalman Filter - https://www.cs.unc.edu/~welch/media/pdf/kalman_intro.pdf</li>


