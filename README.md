# socket

#!/bin/bash

#File: tree-md

tree=$(tree -tf --noreport -I '*~' --charset ascii $1 |
       sed -e 's/| \+/  /g' -e 's/[|`]-\+/ */g' -e 's:\(* \)\(\(.*/\)\([^/]\+\)\):\1[\4](\2):g')

printf "# Project tree\n\n${tree}"

'''bash
├───.anaconda
│   └───navigator
│       ├───content
│       ├───defaults
│       ├───images
│       ├───logs
│       ├───metadata
│       └───scripts
├───.android
│   ├───avd
│   │   ├───Pixel_2_API_30.avd
│   │   │   ├───data
│   │   │   │   └───misc
│   │   │   │       └───pstore
│   │   │   ├───snapshots
│   │   │   │   └───default_boot
│   │   │   └───tmpAdbCmds
│   │   └───Pixel_3a_API_30_x86.avd
│   │       ├───snapshots
│   │       │   └───default_boot
│   │       └───tmpAdbCmds
│   ├───breakpad
│   ├───build-cache
│   │   ├───3.4.2
│   │   ├───3.6.1
│   │   ├───4.0.1
│   │   └───4.0.2
│   ├───cache

'''
