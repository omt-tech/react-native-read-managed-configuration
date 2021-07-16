# react-native-read-managed-configuration

## Note this only works with android. this will not work with ios.

## Getting started

`$ npm install git://github.com/jinpark/react-native-read-managed-configuration.git#master --save`

### Link required after installation if React Native < 0.60

`$ react-native link react-native-read-managed-configuration`

## Usage
```javascript
import ReadManagedConfiguration from 'react-native-read-managed-configuration';

const data = await ReadManagedConfiguration.getAllRestrictions();
// do stuff with data
// data = {managed_config_key1: managed_config_data1, managed_config_key2: managed_config_data2, ...}
```
