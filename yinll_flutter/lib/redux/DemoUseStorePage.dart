import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:yinll_flutter/redux/AppState.dart';
import 'package:yinll_flutter/redux/user.dart';

class DemoUseStorePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    ///通过 StoreConnector 关联 AppState 中的 User
    return new StoreConnector<AppState, User>(
      ///通过 converter 将 AppState 中的 userInfo返回
      converter: (store) => store.state.userInfo,
      ///在 userInfo 中返回实际渲染的控件
      builder: (context, userInfo) {
        return new Scaffold(
          appBar: new AppBar(
            title: new Text('ooooo'),
          ),
          body: new Text(
            "aaa",
            style: Theme.of(context).textTheme.display1,
          ),
        );
      },
    );
  }
}