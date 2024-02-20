<template>
  <div>
    
  <!-- 表格 -->
  <el-table
    v-loading="listLoading"
    :data="list"
    element-loading-text="数据加载中"
    border
    fit
    highlight-current-row
    row-class-name="myClassList">
  
    <el-table-column
      label="序号"
      width="70"
      align="center">
      <template slot-scope="scope">
        {{ (page - 1) * limit + scope.$index + 1 }}
      </template>
    </el-table-column>
  
    <el-table-column label="珠宝信息" width="470" align="center">
      <template slot-scope="scope">
        <div class="info">
          <div class="pic">
            <img :src="scope.row.cover" alt="scope.row.title" width="150px">
          </div>
          <div class="title">
            <a href="">{{ scope.row.title }}</a>
          </div>
        </div>
  
      </template>
    </el-table-column>
  
    <el-table-column label="创建时间" align="center">
      <template slot-scope="scope">
        {{ scope.row.gmtCreate.substr(0, 10) }}
      </template>
    </el-table-column>
    <el-table-column label="发布时间" align="center">
      <template slot-scope="scope">
        {{ scope.row.gmtModified.substr(0, 10) }}
      </template>
    </el-table-column>
    <el-table-column label="价格" width="100" align="center" >
      <template slot-scope="scope">
        {{ Number(scope.row.price) === 0 ? '免费' :
        '¥' + scope.row.price.toFixed(2) }}
      </template>
    </el-table-column>
    <el-table-column prop="buyCount" label="付费人数" width="100" align="center" >
      <template slot-scope="scope">
        {{ scope.row.buyCount }}人
      </template>
    </el-table-column>
  
    <el-table-column label="操作" width="150" align="center">
      <template slot-scope="scope">
        <router-link :to="'/jewellery/info/'+scope.row.id">
          <el-button type="text" size="mini" icon="el-icon-edit">编辑珠宝信息</el-button>
        </router-link>
        <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  
  <!-- 分页 -->
  <el-pagination
    :current-page="page"
    :page-size="limit"
    :total="total"
    style="padding: 30px 0; text-align: center;"
    layout="total, prev, pager, next, jumper"
    @current-change="fetchData"
  />
  </div>

</template>
<script>
import jewellery from '@/api/jewellery/jewellery'
import designer from '@/api/jewellery/designer'
import classofy from '@/api/jewellery/classofy'

export default {

  data() {
    return {
      listLoading: true, // 是否显示loading信息
      list: null, // 数据列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 10, // 每页记录数
      searchObj: {
        classofyParentId: '',
        classofyId: '',
        title: '',
        designerId: ''
      }, // 查询条件
      designerList: [], // 设计师列表
      classofyOneList: [], // 一级分类列表
      classofyTwoList: [] // 二级分类列表,
    }
  },

  created() { // 当页面加载时获取数据
    this.fetchData()
    // 初始化分类列表
    this.initClassofyList()
    // 获取设计师列表
    this.initDesignerList()
  },

  methods: {
    fetchData(page = 1) { // 调用api层获取数据库中的数据
      console.log('加载列表',this.searchObj)
      // 当点击分页组件的切换按钮的时候，会传输一个当前页码的参数page
      // 解决分页无效问题
      this.page = page
      this.listLoading = true
      jewellery.getList(this.page, this.limit, this.searchObj).then(response => {
        // debugger 设置断点调试
        if (response.success === true) {
          this.list = response.data.rows
          this.total = response.data.total
        }
        this.listLoading = false
      })
    },

    initDesignerList() {
      designer.getDesignerListPage(this.page, this.limit, this.searchObj).then(response => {
        this.designerList = response.data.items
      })
    },

    initClassofyList() {
      classofy.getClassofyList().then(response => {
        this.classofyOneList = response.data.items
      })
    },

    classofyLevelOneChanged(value) {
      for (let i = 0; i < this.classofyOneList.length; i++) {
        if (this.classofyOneList[i].id === value) {
          this.classofyTwoList = this.classofyOneList[i].children
          this.searchObj.classofyId = ''
        }
      }
    },

    resetData() {
      this.searchObj = {}
      this.classofyTwoList = [] // 二级分类列表
      this.fetchData()
    }
    ,
        removeDataById(id){//删除设计师
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                jewellery.deleteJewelleryId(id)
             }).then(request => { 
                //提示信息
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
                //回到当前页面
                this.fetchData()
            })
        }
  }
}
</script>
<style scoped>
.myClassList .info {
    width: 450px;
    overflow: hidden;
}
.myClassList .info .pic {
    width: 150px;
    height: 90px;
    overflow: hidden;
    float: left;
}
.myClassList .info .pic a {
    display: block;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
.myClassList .info .pic img {
    display: block;
    width: 100%;
}
.myClassList td .info .title {
    width: 280px;
    float: right;
    height: 90px;
}
.myClassList td .info .title a {
    display: block;
    height: 48px;
    line-height: 24px;
    overflow: hidden;
    color: #00baf2;
    margin-bottom: 12px;
}
.myClassList td .info .title p {
    line-height: 20px;
    margin-top: 5px;
    color: #818181;
}
</style>