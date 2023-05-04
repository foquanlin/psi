<template>
  <order-list catalog="SALE" type="ORDER" :descriptions="descriptions"/>
</template>

<script>
import OrderList from './order-list'
import Options from './options'
export default {
  data () {
    return {
      descriptions: Options.descriptions('SALE', 'ORDER')
    }
  },
  components: {
    OrderList,
    Options
  }
}
</script>
