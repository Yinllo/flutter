import 'package:flutter/material.dart';
import 'MyCustomCircle.dart';
import 'PieData.dart';
import 'dart:ui';

class DemoPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new DemoPageState();
  }
}

class DemoPageState extends State<DemoPage> {

  //数据源 下标  表示当前是PieData哪个对象
  int subscript = 0;
  //数据源
  List<PieData> mData;
  //传递值
  PieData pieData;
  //当前选中
  var currentSelect = 0;


  ///初始化 控制器
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    initData();

  }

  ///空处理   //当整个页面dispose时，记得把控制器也dispose掉，释放内存
  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return _buildHeader();
  }

  Widget _buildHeader() {
    return new Container(
      color: Color(0xfff4f4f4),
      height: 200.0,
      width: 200.0,
      child: new Card(
        margin: const EdgeInsets.all(50.0),
        child: new Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            new Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                new IconButton(
                  icon: new Icon(Icons.arrow_left),
                  color: Colors.green[500],
                  onPressed: _left,
                ),
              ],
            ),

            new Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                new Container(
                  width: 90.0,
                  height: 90.0,
                  padding: const EdgeInsets.only(bottom: 20.0),
                  child: new MyCustomCircle(mData, pieData, currentSelect),
                ),
              ],
            ),

            new Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                new IconButton(
                  icon: new Icon(Icons.arrow_right),
                  color: Colors.green[500],
                  onPressed: _changeData,
                ),
              ],
            ),
//
          ],
        ),
      ),
    );
  }

  ///点击按钮时  改变显示的内容

  void _left() {
    setState(() {
      if (subscript > 0) {
        --subscript;
        --currentSelect;
      }
      pieData = mData[subscript];
    });
  }

  ///改变饼状图
  void _changeData() {
    setState(() {

      if (subscript < mData.length) {
        ++subscript;
        ++currentSelect;
      }
      pieData = mData[subscript];
    });
  }

  //初始化数据源
  void initData() {
    mData = new List();
    PieData p1 = new PieData();
    p1.name = 'A';
    p1.price = 'a';
    p1.percentage = 0.1932;
    p1.color = Color(0xffff3333);

    pieData = p1;
    mData.add(p1);

    PieData p2 = new PieData();
    p2.name = 'B';
    p2.price = 'b';
    p2.percentage = 0.15;
    p2.color = Color(0xffccccff);
    mData.add(p2);

    PieData p3 = new PieData();
    p3.name = 'C';
    p3.price = 'c';
    p3.percentage = 0.1132;
    p3.color = Color(0xffCD00CD);
    mData.add(p3);

    PieData p4 = new PieData();
    p4.name = 'D';
    p4.price = 'd';
    p4.percentage = 0.0868;
    p4.color = Color(0xffFFA500);
    mData.add(p4);

    PieData p5 = new PieData();
    p5.name = 'E';
    p5.price = 'e';
    p5.percentage = 0.18023;
    p5.color = Color(0xff40E0D0);
    mData.add(p5);

    PieData p6 = new PieData();
    p6.name = 'F';
    p6.price = 'f';
    p6.percentage = 0.12888;
    p6.color =Color(0xffFFFF00);
    mData.add(p6);

    PieData p7 = new PieData();
    p7.name = 'G';
    p7.price = 'g';
    p7.percentage = 0.0888;
    p7.color = Color(0xff00ff66);
    mData.add(p7);

    PieData p8 = new PieData();
    p8.name = 'H';
    p8.price = 'h';
    p8.percentage = 0.06;
    p8.color = Color(0xffD9D9D9);
    mData.add(p8);
  }
}
