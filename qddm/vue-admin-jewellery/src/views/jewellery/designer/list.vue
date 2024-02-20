<template>
    <div>
            <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="designerQuery.name" placeholder="设计师名字"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="designerQuery.level" clearable placeholder="设计师头衔">
          <el-option :value="1" label="高级技师"/>
          <el-option :value="2" label="技师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="选择时间">
        <el-date-picker
          v-model="designerQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="designerQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
        <!--表格-->
        <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="姓名" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level===1?'高级技师':'技师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="简介" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/designer/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
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
      @current-change="getList"
    />
    </div>
</template>
<script>
//引入调用designer.js文件
import designer from '@/api/jewellery/designer'
export default{
    //写核心代码的位置
    data(){//定义变量和初始值
        return{
            list:null,//查询之后接口返回集合
            page:1,//当前页
            limit:10,//每页记录数
            total:0,//总记录数
            designerQuery:{}//条件封装对象
        }
    },
    created(){//页面渲染之前执行，一般调用methods定义的方法
        this.getList()
    },
    methods:{//创建具体的方法，调用designer.js定义的方法
        //设计师列表方法
        getList(page = 1){
            this.page = page
            designer. getDesignerListPage(this.page,this.limit,this.designerQuery)
            .then((result) => {//请求成功
                //request接口返回的数据
               this.list = result.data.rows
               this.total = result.data.total
               console.log(this.list)
               console.log(this.total)
            }).catch((err) => {//请求失败
                console.log(err)
            });
        },
        resetData(){//清空的方法
            //将表单的数据清空
            this.designerQuery = {}
            //查询所有设计师的数据
            this.getList()
        },
        removeDataById(id){//删除设计师
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                designer.deleteDesignerId(id)
             }).then(request => { 
                //提示信息
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
                //回到当前页面
                this.getList()
            })
        }
    }
}
</script>