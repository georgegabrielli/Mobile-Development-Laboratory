import React, {Component} from 'react';
import {AppRegistry, Text, View, ListView, StyleSheet, TouchableOpacity, FlatList, RefreshControl} from 'react-native';
import {Car} from "./Car";


export default class CarList extends Component {

    list = [
        new Car("asfasdas", "bmw", "1er", 2011, 1993),
        new Car("asfasdas", "bmw", "1er", 2011, 1993),
        new Car("asfasdas", "bmw", "1er", 2011, 1993),
        new Car("asfasdas", "bmw", "1er", 2011, 1993),
        new Car("asfasdas", "bmw", "1er", 2011, 1993),
        new Car("asfasdas", "bmw", "1er", 2011, 1993)
    ];

    constructor() {
        super();
        this.onRefresh = this.onRefresh.bind(this);
        this.state = {
            refreshing: false,
        };
    }

    onRefresh() {
        this.setState({refreshing: true});
        this.setState({refreshing: false});
    }



    render() {
        const {navigate} = this.props.navigation;
        return (
            <FlatList containerStyle={{marginBottom: 20}}
                      data={this.list}
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
                                  }))}>
                              <Text style={{flex: 1, fontSize: 16}}>
                                  {item.getmake()} {item.getmodel()}
                              </Text>
                          </TouchableOpacity>
                      }
            />
        )
    }
}

const styles = StyleSheet.create({

    listItem: {
        backgroundColor: '#FFAABB',
        marginBottom: 8
    }

});

