import React, {Component} from 'react';
import {
    Alert, AsyncStorage, Text, View, ListView, StyleSheet, TouchableOpacity, FlatList, RefreshControl,
    Button, TouchableHighlight
} from 'react-native';
import {Car} from "./Car";


export default class CarList extends React.Component {
    constructor() {
        super();
        this.onRefresh = this.onRefresh.bind(this);
        this.car_list = [];
        this.state = {
            refreshing: false,
        };
    }

    onRefresh() {
        this.setState({refreshing: true});
        this.car_list = [];
        AsyncStorage.getAllKeys().then(
            (value1) => {
                for (let i = 0; i < value1.length; i++) {
                    AsyncStorage.getItem(value1[i]).then((value2) => {
                        let jsonCar = JSON.parse(value2);
                        let car = new Car(jsonCar['chassisCode'], jsonCar['make'], jsonCar['model'],
                            jsonCar['year'], jsonCar['cubicCapacity']);
                        car.setIsNew(false);
                        this.car_list.push(car);
                    }).done();
                }
            }
        ).then(this.setState({refreshing: false})).done();
        this.setState({refreshing: false});
    }

    componentWillMount() {
        this.onRefresh();
    }

    render() {
        const {navigate} = this.props.navigation;

        if (this.state.refreshing) {
            return <View><Text>Loading.......</Text></View>;
        }
        return (
            <View style={styles.MainContainer}>

                <FlatList containerStyle={{marginBottom: 20}}
                          data={this.car_list}
                          extraData={this.state}
                          refreshControl={<RefreshControl
                              refreshing={this.state.refreshing}
                              onRefresh={this.onRefresh}
                          />}
                          keyExtractor={(item, index) => index}
                          renderItem={({item}) =>
                              <TouchableOpacity
                                  style={{
                                      height: 60,
                                      backgroundColor: "#ffffff",
                                      borderRadius: 4,
                                      borderWidth: 0.5,
                                      borderColor: '#d6d7da',
                                  }}
                                  onPress={() => (
                                      navigate("EditCar", {
                                          item: item,
                                          refresh: this.onRefresh
                                      }))}

                                  onLongPress={() => {
                                      Alert.alert(
                                          'Warning',
                                          'Do you want to delete this car?',
                                          [
                                              {
                                                  text: 'OK', onPress: () => {
                                                  AsyncStorage.removeItem(item.getchassisCode())
                                                      .then(alert("Item was deleted!")).then(this.onRefresh).done();

                                              }
                                              },
                                              {
                                                  text: 'Cancel',
                                                  onPress: () => console.log('Cancel Pressed'),
                                                  style: 'cancel'
                                              }
                                          ],
                                          {cancelable: false}
                                      )


                                  }}>
                                  <Text style={{flex: 1, fontSize: 16}}>
                                      {item.getmake()} {item.getmodel()}
                                  </Text>
                              </TouchableOpacity>
                          }
                />

                <TouchableOpacity style={styles.addButton}
                                  underlayColor='#ff7043' onPress={() => {
                    this.addCar()
                }}>
                    <Text style={{fontSize: 50, color: 'white'}}>+</Text>
                </TouchableOpacity>


            </View>
        )
    }

    addCar() {
        const {navigate} = this.props.navigation;
        navigate('EditCar', {
            item: new Car(),
            refresh:this.onRefresh
        });
    }
}

const styles = StyleSheet.create({

    MainContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5F5F5'
    },


    listItem: {
        backgroundColor: '#FFAABB',
        marginBottom: 8
    },

    addButton: {
        backgroundColor: '#ff5722',
        borderColor: '#ff5722',
        borderWidth: 1,
        height: 60,
        width: 60,
        borderRadius: 50,
        alignItems: 'center',
        justifyContent: 'center',
        position: 'absolute',
        bottom: 20,
        right: 20,
        shadowColor: "#000000",
        shadowOpacity: 0.8,
        shadowRadius: 2,
        shadowOffset: {
            height: 1,
            width: 0
        }
    }

});

