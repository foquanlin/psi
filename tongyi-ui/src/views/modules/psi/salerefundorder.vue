<template>
  <order-list catalog="SALE" type="REFUND" :descriptions="descriptions"/>
</template>

<script>
import OrderList from './order-list'
import Options from './options'
export default {
  data () {
    return {
      descriptions: Options.descriptions('SALE', 'REFUND')
    }
  },
  components: {
    OrderList,
    Options
  }
}
</script>
