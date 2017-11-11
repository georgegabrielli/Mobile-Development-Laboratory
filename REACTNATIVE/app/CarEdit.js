import * as React from "react";
import {TextInput, View, StyleSheet, Text, Button} from "react-native";
import Communications from 'react-native-communications';
export class CarEdit extends React.Component {
    constructor() {
        super();
        this.make = '';
        this.model = '';
        this.cubicCapaity = '';
        this.year = '';
    }

    render() {
        const {goBack} = this.props.navigation;
        const {item} = this.props.navigation.state.params;
        const {refresh} = this.props.navigation.state.params;
        this.make = item.getmake();
        this.model = item.getmodel();
        this.cubicCapacity = item.getcubicCapacity();
        this.year = item.getyear();

        return (
            <View style={styles.container}>
                <Text>Make</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.make = text}
                    placeholder={"Make"}>
                    {this.make}
                </TextInput>
                <Text>Model</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.model = text}
                    placeholder={"Model"}>
                    {this.model}
                </TextInput>
                <Text>Cubic Capacity</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.cubicCapacity = text}
                    placeholder={"Cubic Capacity"}>
                    {this.cubicCapaity}
                </TextInput>
                <Text>Year</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.year = text}
                    placeholder={"Year"}>
                    {this.year}
                </TextInput>
                <Button title="Save" onPress={() => {
                    item.setmake(this.make);
                    item.setmodel(this.model);
                    item.setcubicCapacity(this.cubicCapaity);
                    item.setyear(this.year);
                    Communications.email(["gabrielli.george@gmail.com", "gabrielli.george@gmail.com"], null, null, "CAR UPDATED", item.getchassisCode());
                    refresh();
                    goBack();
                }}>
                </Button>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: "#ffffff",
    },

});