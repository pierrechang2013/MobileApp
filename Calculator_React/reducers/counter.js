import * as types from '../actions/actionTypes';

const initialState = {
  content: '',
};

//这是一个方法 ，用来计算，方法counter 就是reducer,创建store需要
//createStore((state,action)=>{},{})
//(state,action)=>{}就是reduce,逗号以后的，是preloadedStater,即初始状态
//reducer的写法有要求，包含state和action，state的初始值，就是preloadedStater，
//需要像下面这么写，否则第一运行取不到初始值，换句话说，不用写给preloadedStater赋值，就只用reducer就行了

//Redux的reducer就得这么写一个state,一个action，这个action是一个对象，里面应该包括一个type方便区分不同的操作
//把state放到reducer里，reducer里操作state然后返回

//view发出的通知 通过action，改变 state，从而改变view。
//修改 state的唯一办法是使用 action
export default function counter(state = initialState, action = {}) {
  // console.log('counter 方法注册 action 当前是' + JSON.stringify(action));
  // console.log('!!!!!!!!!!!!!!!');

  // if (state.content.startsWith('0')) {
  //   state.content = state.content.substring(1);
  // }

  // let str = state.content;
  // if (str.startsWith('0(') || str.startsWith('0(') || str.startsWith('0=')) {
  //   state.content = str.substring(2);
  // } else if (false) {
  //   state.content = content.substring(1);
  // }
  state.content = validPhrase(state.content);
  
  switch (action.type) {
    //用action.type来区分操作的类

    case types.symCan:
      //console.log('############');
      return {
        ...state,
        content: '',
      };

    case types.symDel:
      var currentContent = String(state.content);
      console.log('currentContent:' + currentContent);
      return {
        ...state,
        content: currentContent.substring(0, currentContent.length - 1),
      };

    case types.symEqu:
      console.log('当前算式' + state.content);

      var result = '';
      try {
        result = eval(state.content);
      } catch (e) {
        result = 'mauvaise entrée';
        console.log(e);
      }
      return {
        //计算
        ...state, //就得这么写，...state，

        content: result,
      };
    case types.number0:
      return {
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '0'),
      };
    case types.number1:
      return {
        ...state,
        content: (state.content = state.content + '1'),
      };
    case types.number2:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '2'),
      };
    case types.number3:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '3'),
      };
    case types.number4:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '4'),
      };
    case types.number6:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '6'),
      };

    case types.number5:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '5'),
      };

    case types.number7:
      return {
        //计算
        ...state,
        content: (state.content = state.content + '7'),
      };

    case types.number8:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '8'),
      };

    case types.number9:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '9'),
      };
    case types.symAdd:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '+'),
      };
    case types.symRed:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '-'),
      };
    case types.symMul:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '*'),
      };
    case types.symDiv:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '/'),
      };
    case types.symPoint:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '.'),
      };

    case types.symParLeft:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + '('),
      };
    case types.symParRight:
      return {
        //计算
        ...state, //就得这么写，...state，
        content: (state.content = state.content + ')'),
      };
    default:
      //  return {
      //    ...state,
      //    content: state.content + action.type,
      //  };

      return state;
  }
}

function validPhrase(content) {
  //调整算式，以免计算时候eval方法计算不了而报错
  let str = String(content);
  if (str.startsWith('0(') || str.startsWith('0(') || str.startsWith('0=')) {
    str = str.substring(2);
  } else if (false) {
    state.content = content.substring(1);
  }

  return str;
}
