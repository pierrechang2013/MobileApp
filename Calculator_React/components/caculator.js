import React, {Component} from 'react';
import {StyleSheet, View, Text, TouchableOpacity} from 'react-native';


const styles = StyleSheet.create({
  container: {
    //整个计算器界面的外边距和距离顶部的距离
    marginTop: 80,
    flex: 1,
    marginLeft: 18,
    marginRight: 18,
  },
  buttonContainer: {
    //每一行按钮的布局，高度为100，主轴是水平的，沿着主轴分布排列
    height: 100,
    flexDirection: 'row',
    marginLeft: 8,
    marginRight: 8,
    justifyContent: 'space-around',
    alignItems: 'flex-start',
  },
  topButton: {
    //上面一行的按钮颜色不一样，主要是设置颜色的差别
    width: 80,
    height: 80,
    marginTop: 10,
    borderRadius: 160,
    alignItems: 'center',
    backgroundColor: 'rgb(165, 165, 165)',
    justifyContent: 'center',
  },
  rightButton: {
    //右边的按钮颜色也不同
    //设置原型按钮，通过redius指定为长宽的两倍即可。
    width: 80,
    height: 80,
    marginTop: 10,
    borderRadius: 160,
    alignItems: 'center',
    backgroundColor: 'rgb(240, 153, 55)',
    justifyContent: 'center',
  },
  centerButton: {
    width: 80,
    height: 80,
    marginTop: 10,
    borderRadius: 160,
    alignItems: 'center',
    backgroundColor: 'rgb(51, 51, 51)',
    justifyContent: 'center',
  },
  zeroButton: {
    //按钮0占据两个按钮的位置，所以长度要改变
    width: 160,
    height: 80,
    marginTop: 10,
    borderRadius: 160,
    alignItems: 'center',
    backgroundColor: 'rgb(51, 51, 51)',
    justifyContent: 'center',
  },
  textStyle: {
    //设置按钮的字体风格和大小
    textAlign: 'center',
    color: 'white',
    fontSize: 25,
  },
  resultText: {
    //设置显示结果的字体风格和大小
    textAlign: 'right',
    fontSize: 35,
    color: 'black',
    height: 50,
    backgroundColor: 'rgb(238,238,238)',
  },
  button: {
    width: 100,
    height: 30,
    padding: 10,
    backgroundColor: 'lightgray',
    alignItems: 'center',
    justifyContent: 'center',
    margin: 3,
  },
});

export default class Caculator extends Component {
  constructor(props) {
    super(props);
  }

  render() {
     
    const {
      content,
      equal,
      del,
      cancelAll,
      putNumber0,
      putNumber1,
      putNumber2,
      putNumber3,
      putNumber4,
      putNumber5,
      putNumber6,
      putNumber7,
      putNumber8,
      putNumber9,
      putSymPoint,
      putSymAdd,
      putSymRed,
      putSymMul,
      putSymDiv,
      putSymParLeft,
      putSymParRight,
    } = this.props;

     
    // console.log('class Caculator  content:' + this.props.content);
    // console.log('class Caculator  equal:' + equal);
    // console.log('class Caculator  del:' + del);
    // console.log('class Caculator  cancelALL:' + cancelAll);
    // console.log('class Caculator  putNumber:' + putNumber1);

    return (
      <View style={styles.container}>
        <Text style={styles.resultText}>{content}</Text>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.topButton} onPress={cancelAll}>
            <Text style={styles.textStyle}>AC</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.topButton} onPress={del}>
            <Text style={styles.textStyle}>←</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.centerButton} onPress={putSymPoint}>
            <Text style={styles.textStyle}>.</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.rightButton} onPress={putSymDiv}>
            <Text style={styles.textStyle}>÷</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.centerButton} onPress={putNumber7}>
            <Text style={styles.textStyle}>7</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putNumber8}>
            <Text style={styles.textStyle}>8</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putNumber9}>
            <Text style={styles.textStyle}>9</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.rightButton} onPress={putSymMul}>
            <Text style={styles.textStyle}>×</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.centerButton} onPress={putNumber4}>
            <Text style={styles.textStyle}>4</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.centerButton} onPress={putNumber5}>
            <Text style={styles.textStyle}>5</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putNumber6}>
            <Text style={styles.textStyle}>6</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.rightButton} onPress={putSymRed}>
            <Text style={styles.textStyle}>—</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.centerButton} onPress={putNumber1}>
            <Text style={styles.textStyle}>1</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putNumber2}>
            <Text style={styles.textStyle}>2</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putNumber3}>
            <Text style={styles.textStyle}>3</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.rightButton} onPress={putSymAdd}>
            <Text style={styles.textStyle}>+</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.centerButton} onPress={putNumber0}>
            <Text style={styles.textStyle}>0</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.centerButton} onPress={putSymParLeft}>
            <Text style={styles.textStyle}>(</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.centerButton}
            onPress={putSymParRight}>
            <Text style={styles.textStyle}>)</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.rightButton} onPress={equal}>
            <Text style={styles.textStyle}>=</Text>
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}
