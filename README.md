# brs-java
Brickadia savefile reader/writer for Java

## Example usage 
make all bricks in a save the default colorset's orange color

```Java
SaveData brickadiaCity = BRS.readSave("example_saves/BrickadiaCityQA.brs");
for (Brick brick : brickadiaCity.getBricks())
    brick.setColor(ColorMode.ORANGE);
BRS.writeSave("example_saves/OrangeCity.brs", brickadiaCity);
```

add your own screenshot to a save
```Java
SaveData brickadiaCity = BRS.readSave("example_saves/BrickadiaCityQA.brs");
brickadiaCity.setScreenshot(new File("custom_screenshot.png"));
BRS.writeSave("example_saves/CustomScreenshot.brs", brickadiaCity);
```

create your own save from scratch
```Java
SaveData save = new SaveData();
save.setDescription("My very own save!");
save.setBrickAssets(List.of("PB_DefaultBrick"));

// Add a brick to the save
Brick b = new Brick();
b.setSize(new Vec3(5, 5, 6));
b.setPosition(new Vec3(0, 0, 6));
save.getBricks().add(b);

BRS.writeSave("brick.brs", save);
```

## Including
Replace Tag with most recent tag or commit hash in repo

### Maven

```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```
<dependency>
    <groupId>com.github.brickadia-community</groupId>
    <artifactId>brs-java</artifactId>
    <version>Tag</version>
</dependency>
```

### Gradle

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  implementation 'com.github.User:Repo:Tag'
}
```
