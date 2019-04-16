<template>
  <v-card>
    <!--卡片头部-->
    <v-card-title>
    <v-btn color="primary" @click="addBrand">新增厂商</v-btn>
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


      <!--弹出的对话框-->
      <v-dialog max-width="500" v-model="show" persistent>
        <v-card>
          <!--对话框的标题-->
          <v-toolbar dense dark color="primary">
            <v-toolbar-title>新增品牌</v-toolbar-title>
          <v-spacer/>
          <!--关闭窗口的按钮-->
          <v-btn icon @click="closeWindow"><v-icon>close</v-icon></v-btn>
          </v-toolbar>
          <!--对话框的内容，表单-->
          <v-card-text class="px-5" >
            <my-brand-form @close="closeWindow"/>
          </v-card-text>
        </v-card>
      </v-dialog>

    </v-card>
</template>

<script>

  import MyBrandForm from './MyBrandForm'

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
        ],
        show: false
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
      },
      addBrand(){
        // 控制弹窗可见：
        this.show = true;
      },
      closeWindow(){
        // 关闭窗口
        this.show = false;
        // 重新加载数据
        this.getDataFromServer();
      }
    },
    components:{
      MyBrandForm
    }

  }
</script>

<style scoped>

</style>
