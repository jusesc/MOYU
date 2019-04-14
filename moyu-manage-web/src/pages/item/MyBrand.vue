<template>
  <v-card>
    <!--卡片头部-->
    <v-card-title>
    <v-btn color="primary">新增</v-btn>
      <!--空间隔离组件-->
      <v-spacer />
      <!--搜索框，与search属性关联-->
      <v-text-field label="输入关键字搜索" v-model="search" append-icon="search" hide-details/>
    </v-card-title>
    <!-- 分割线 -->
    <v-divider/>
    <!--卡片的中部-->
    <v-data-table
      :headers="headers"
      :items="brands"
      :search="search"
      :pagination.sync="pagination"
      :total-items="totalBrands"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center"><img :src="props.item.image"></td>
        <td class="text-xs-center">{{ props.item.letter }}</td>
        <td class="justify-center layout">
          <v-btn color="info">编辑</v-btn>
          <v-btn color="warning">删除</v-btn>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
  export default {
    name: "my-brand",
    data() {
      return {
        search: '', // 搜索过滤字段
        totalBrands: 0, // 总条数
        brands: [], // 当前页厂商数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: 'id', align: 'center', value: 'id', sortable: true},
          {text: '厂商名称', align: 'center',  value: 'name', sortable: false},
          {text: 'LOGO', align: 'center',value: 'image',  sortable: false},
          {text: '首字母', align: 'center', value: 'letter', sortable: true},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ]
      }
    },
    mounted(){ // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },
    methods:{
      getDataFromServer(){ // 从服务的加载数的方法。
        // 伪造假数据
        const brands = [
          {
            "id": 2032,
            "name": "Apple",
            "image": "http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg",
            "letter": "O",
            "categories": null
          },
          {
            "id": 2033,
            "name": "Banana",
            "image": "http://img12.360buyimg.com/popshop/jfs/t18361/122/1318410299/1870/36fe70c9/5ac43a4dNa44a0ce0.jpg",
            "letter": "F",
            "categories": null
          },
          {
            "id": 2034,
            "name": "Carbon",
            "image": "http://img10.360buyimg.com/popshop/jfs/t5662/36/8888655583/7806/1c629c01/598033b4Nd6055897.jpg",
            "letter": "H",
            "categories": null
          },
          {
            "id": 2036,
            "name": "Dog",
            "image": "http://img10.360buyimg.com/popshop/jfs/t2521/347/883897149/3732/91c917ec/5670cf96Ncffa2ae6.jpg",
            "letter": "K",
            "categories": null
          },
          {
            "id": 2037,
            "name": "Fat",
            "image": "http://img13.360buyimg.com/popshop/jfs/t3511/131/31887105/4943/48f83fa9/57fdf4b8N6e95624d.jpg",
            "letter": "M",
            "categories": null
          }
        ];
        // 模拟延迟一段时间，随后进行赋值
        setTimeout(() => {
          // 然后赋值给brands
          this.brands = brands;
          this.totalBrands = brands.length;
          // 完成赋值后，把加载状态赋值为false
          this.loading = false;
        },400)
      }
    }
  }
</script>

<style scoped>

</style>
