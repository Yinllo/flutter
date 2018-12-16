import 'package:yinll_flutter/redux/user.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:yinll_flutter/redux/user_reducer.dart';

class AppState {

  User userInfo;

  ///构造方法
  AppState({this.userInfo});

}

  ///创建 Reducer
  ///源码中 Reducer 是一个方法 typedef State Reducer<State>(State state, dynamic action);
  ///我们自定义了 appReducer 用于创建 store
  AppState appReducer(AppState state, action) {
    return AppState(

      ///通过 UserReducer 将 GSYState 内的 userInfo 和 action 关联在一起
      userInfo: UserReducer(state.userInfo, action),


    );
  }
