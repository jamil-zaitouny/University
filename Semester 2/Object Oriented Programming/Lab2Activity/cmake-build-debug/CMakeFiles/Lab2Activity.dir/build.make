# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Lab2Activity.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Lab2Activity.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Lab2Activity.dir/flags.make

CMakeFiles/Lab2Activity.dir/main.cpp.obj: CMakeFiles/Lab2Activity.dir/flags.make
CMakeFiles/Lab2Activity.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Lab2Activity.dir/main.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Lab2Activity.dir\main.cpp.obj -c C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\main.cpp

CMakeFiles/Lab2Activity.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Lab2Activity.dir/main.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\main.cpp > CMakeFiles\Lab2Activity.dir\main.cpp.i

CMakeFiles/Lab2Activity.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Lab2Activity.dir/main.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\main.cpp -o CMakeFiles\Lab2Activity.dir\main.cpp.s

# Object files for target Lab2Activity
Lab2Activity_OBJECTS = \
"CMakeFiles/Lab2Activity.dir/main.cpp.obj"

# External object files for target Lab2Activity
Lab2Activity_EXTERNAL_OBJECTS =

Lab2Activity.exe: CMakeFiles/Lab2Activity.dir/main.cpp.obj
Lab2Activity.exe: CMakeFiles/Lab2Activity.dir/build.make
Lab2Activity.exe: CMakeFiles/Lab2Activity.dir/linklibs.rsp
Lab2Activity.exe: CMakeFiles/Lab2Activity.dir/objects1.rsp
Lab2Activity.exe: CMakeFiles/Lab2Activity.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Lab2Activity.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Lab2Activity.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Lab2Activity.dir/build: Lab2Activity.exe

.PHONY : CMakeFiles/Lab2Activity.dir/build

CMakeFiles/Lab2Activity.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Lab2Activity.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Lab2Activity.dir/clean

CMakeFiles/Lab2Activity.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug C:\Users\Jamil\Desktop\University\Homeworks\OOP\Lab2Activity\cmake-build-debug\CMakeFiles\Lab2Activity.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Lab2Activity.dir/depend

