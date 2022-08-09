import React, {Component} from 'react';
import { createStore, applyMiddleware, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';

import * as reducers from '../reducers';
import CounterApp from './counterApp';

const createStoreWithMiddleware = applyMiddleware(thunk)(createStore);
const reducer = combineReducers(reducers);
//reducers from '../reducers里是方法，是action，用来
const store = createStoreWithMiddleware(reducer);

export default class App extends Component {
  render() {
    return (
      //这是入口
      //Provider在根组件外面包了一层，这样一来，App的所有子组件就默认都可以拿到state了。
      <Provider store={store}>
        <CounterApp />
      </Provider>
    );
  }
}
