# CuckooBird
简介：这是一个基于java的布谷鸟闯关游戏，摁上键控制鸟的位置穿过管道间的缝隙，需要做碰撞检测，监听键盘事件，背景图片的切换，障碍物管道产生时y轴
上需要随机位置。对于鸟没有做出界处理，没有做与地面的碰撞检测。

1.项目只是做了简单的解耦处理，大量逻辑在Cuckoo.java中编写  
2.逻辑分析包括以下几部分：  
  窗口、背景、移动墙、布谷鸟、障碍物、背景音乐、音效、欢迎界、game over，对于窗口、鸟、障碍物理应抽象为具体的entity，但由于项目较小，没做处理  
3.由几个关键的布尔类型变量start,crash,over是产生键键盘事件时用来控制界面显示的弹框的   
4.操作：空格键开始游戏，enter键取消"game over"弹框，摁空格键继续，上键(up)是对鸟的控制  
5.工具类ImageMgr专用于读取图片  
6.Cuckoo类中集合了大量的静态属性，导致类有些臃肿  

#唯一的心得：  
  当鸟碰撞后，弹出gameover，想做出可以重新开始的效果，想了很久，最后将最关键的那几个boolean类型变量start，crash，over，以及鸟和障碍物偏移的距离设为最初的  
值，实现了重开游戏的效果，在reopen()中。  
 
![image](https://user-images.githubusercontent.com/90468877/134829533-eaa7e0e4-cec7-452c-bbad-5fd6a1f34924.png)![image](https://user-images.githubusercontent.com/90468877/134829583-9e4c700f-43ed-4da5-9234-b92df8fee4ff.png)![image](https://user-images.githubusercontent.com/90468877/134829560-a3e6e2fd-6652-4251-becf-7955aaa877dd.png)

