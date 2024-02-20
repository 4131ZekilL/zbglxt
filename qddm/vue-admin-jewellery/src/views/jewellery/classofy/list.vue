<template>
    <div class="app-container">
      <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
  
      <el-tree
        ref="classofyTree"
        :data="classofyList"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all
      />
  
    </div>
  </template>
  
  <script>
  import classofy from '@/api/jewellery/classofy'
  export default {
  
    data() {
      return {
        filterText: '',
        classofyList: [],
        defaultProps: {
          children: 'children',
          label: 'title'
        }
      }
    },
    watch: {
      filterText(val) {
        this.$refs.classofyTree.filter(val)
      }
    },
  
    created() {
      this.getAllClassofyList()
    },
  
    methods: {
      getAllClassofyList() {
        classofy.getClassofyList().then(response => {
            this.classofyList = response.data.list         
        })
      },
      filterNode(value, data) {
        if (!value) return true
        return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
      }
    }
  }
  </script>