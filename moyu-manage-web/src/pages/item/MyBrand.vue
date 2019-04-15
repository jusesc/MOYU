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
    watch: {
        pagination: {
          deep: true,
          handler(){
            this.getDataFromServer();
          }
        },
        search: {
          handler(){
            this.getDataFromServer();
          }
        }
    },
    mounted(){ // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },
    methods:{
      getDataFromServer(){ // 从服务的加载数的方法。
        // 发起get请求
        this.$http.get("/item/brand/page",{
          params: {
            key: this.search, // 搜索条件
            page: this.pagination.page, // 当前页
            rows: this.pagination.rowsPerPage, // 每页条数
            sortBy: this.pagination.sortBy, // 排序字段
            desc: this.pagination.descending // 是否降序
          }
        }).then(resp => {  // 箭头函数 返回请求成功后的内容
            console.log(resp);
            // 将得到的数据赋值给页面属性
            this.brands = resp.data.items; // 当前页面数据
            this.totalBrands = resp.data.total;// 数据总条数
            // 加载赋值后, 把加载状态赋值为false
            this.loading = false;
          });

        /*// 模拟延迟一段时间，随后进行赋值
        setTimeout(() => {
          // 然后赋值给brands
          this.brands = brands;
          this.totalBrands = brands.length;
          // 完成赋值后，把加载状态赋值为false
          this.loading = false;
        },400)*/
      }
    }
  }
</script>

<style scoped>

</style>
