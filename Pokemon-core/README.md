# Pokemon desktop
## Building project
Either use your favorite IDE with some recent gradle support or use the following in a terminal (linux example).


```
/path/to/gradle-version/bin/gradle assembleDist
/path/to/gradle-version/bin/gradle run
```

The first command build the whole project and assemble the result in 3 different packages located in `build/distributions`:  
 * `A jar` that has no use for now since the c++ dependencies of libGDX must be loaded in a currently unsupported way.
 * `Two archives (a zip and a tar)`  
  
The last two packages contain everything needed to play the game, including launch scripts in their `bin` folder for both Unix and Windows.  
  
As tool gradle provide the `tasks` task to list all the available options including the ones from imported plugins.  
A `clean` task also allows to remove temporary build files but some seems to remain nevertheless, so consider this when encountering some build errors.