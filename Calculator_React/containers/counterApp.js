'use strict';

import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import Caculator from '../components/caculator';
import * as caculatorActions from '../actions/caculatorActions';
import { connect } from 'react-redux';

// @connect(state => ({
//   state: state.counter
// }))
class CounterApp extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    //作为App的子,最外层的被App包裹，取App的props
    //创建对象，包含state和actions,把父类对象赋给当前对象
    //说明state和actions在props里存在
    //从父里来
    const {state, actions} = this.props;

    console.log('counterApp  this.props:' + this.props);
    console.log('counterApp  state content:' + state.content);
    console.log('counterApp  actions:' + a(this.props.actions));


    return (
      <Caculator
        //通过connect给到子里去
        //这里的state.count是counter的返回值，通过把父的state里的counter付给自己的state
        //再取出自己的state(counter)里的count,赋给子
        content={state.content}
        //通过connect给到子
        //这里的actions通过connect被赋值，
        {...actions}
      />
    );
  }
}


//每层都需要connect对store中的对象进行绑定，对方法进行映射
export default connect(
  //第一个方法
  //该方法允许我们将store中的数据作为props绑定到组件中，
  //只要store发生了变化就会调用mapStateToProps方法，方法名随便写，这里就是这个state为参数的匿名函数
  //mapStateProps返回的结果必须是一个纯对象，这个对象会与组件的props合并
  state => ({
    //第一个state是自己在组件中定义的，将来在组件中通过 this.props.state来引入
    //第二个则是在store中的存入的counter对象，就是Reduce里的counter()返回值state
    state: state.counter,
  }),

  dispatch => ({
    actions: bindActionCreators(caculatorActions, dispatch),
  }),
)(CounterApp);
/*
   该方法允许我们将action作为props绑定到组件中，
   用于建立组件跟store.dispatch的映射关系，可以是一个object，也可以是一个函数 
   如果传递的是一个对象，那么每个定义在该对象的函数都将被当作 Redux action creator，
   对象所定义的方法名将作为属性名；每个方法将返回一个新的函数，函数中 dispatch 方法会将 action creator 的返回值作为参数执行。这些属性会被合并到组件的 props 中；
   如果传递的是一个函数，
   可以直接传入dispatch,ownProps,定义UI组件如何发出action，
   实际上就是要调用dispatch方法

  */
  //分发方法，把counterActions（里面都是方法，就是返回属性为type对象）和dispatch绑定
