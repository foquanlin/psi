<template>
  <order-list catalog="BUY" type="REFUND" :descriptions="descriptions"/>
</template>

<script>
import OrderList from './order-list'
import Options from './options'
export default {
  data () {
    return {
      descriptions: Options.descriptions('BUY', 'REFUND')
    }
  },
  components: {
    OrderList,
    Options
  }
}
</script>
