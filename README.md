# ConponentDependency
Author
1. Vittunyuta Maeprasart 5910545019
2. Chawakorn Suprephe 5910545671

## Selected Project
Apache Kafka project
[Project Information](https://projects.apache.org/project.html?kafka), [Download](http://kafka.apache.org/downloads.html)

## Graph using RStudio
![Graph1](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Graph1")
![Graph2](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Graph2")

**Not well-designed packages**
1. ไม่ควรให้ stable -> flexible [stableคือI=0] [flexibleคือI=1] วิธีแก้คือ ให้สร้างคลาส stable เพิ่มมาอันนึง เป็น stable -> newStable <- flexible (ช่วยลดค่า I ใช้หลักการของ DIP)

**Analysis and Suggestion**
- using OCP principle
- stable = astract
- instable = flexible = concrete
