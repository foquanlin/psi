<template>
  <order-list catalog="BUY" type="ORDER" :descriptions="descriptions"/>
</template>

<script>
import OrderList from './order-list'
import Options from './options'
export default {
  data () {
    return {
      descriptions: Options.descriptions('BUY', 'ORDER')
    }
  },
  components: {
    OrderList,
    Options
  }
}
</script>
