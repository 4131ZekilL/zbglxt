<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 设计师介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">设计师介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
          <!-- 设计师基本信息 开始 -->
    <section class="fl t-infor-box c-desc-content">
        <div class="mt20 ml20">
            <section class="t-infor-pic">
                <img :src="designer.avatar" :alt="designer.name">
            </section>
            <h3 class="hLh30">
                <span class="fsize24 c-333">{{ designer.name }}
                    &nbsp;
                    {{ designer.level===1?'高级技师':'技师' }}
                </span>
            </h3>
            <section class="mt10">
                <span class="t-tag-bg">{{ designer.intro }}</span>
            </section>
            <section class="t-infor-txt">
                <p class="mt20">{{ designer.career }}</p>
            </section>
            <div class="clear"/>
        </div>
    </section>
    <!-- /设计师基本信息 结束 -->
        <div class="clear"/>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac">
              <span class="c-333">设计的珠宝</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- 无数据提示 开始-->
        <section class="no-data-wrap" v-if="jewelleryList.total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
        </section>
        <!-- /无数据提示 结束-->

          <!-- 珠宝列表 开始-->
        <article class="comm-course-list">
          <ul class="of">
            <li v-for="jewellery in jewelleryList" :key="jewellery.id">
              <div class="cc-l-wrap">
                <section class="course-img">
                  <img :src="jewellery.cover" class="img-responsive">
                  <div class="cc-mask">
                    <a :href="'/jewellery/'+jewellery.id" title="开始学习" target="_blank" class="comm-btn c-btn-1">珠宝详情</a>
                  </div>
                </section>
                <h3 class="hLh30 txtOf mt10">
                  <a
                    :href="'/jewellery/'+jewellery.id"
                    :title="jewellery.title"
                    target="_blank"
                    class="course-title fsize18 c-333">{{ jewellery.title }}</a>
                </h3>
              </div>
            </li>
          </ul>
          <div class="clear"/>
        </article>
        <!-- /珠宝列表 结束-->
        </div>
      </section>
    </section>
    <!-- /设计师介绍 结束 -->
  </div>
</template>
<script>
import designer from "@/api/designer"
export default {
  asyncData({ params, error }) {
    return designer.getById(params.id).then(response => {
      console.log(response);
      return { 
        designer: response.data.data.designer,
        jewelleryList: response.data.data.jewelleryList
      }
    })
  }
}
</script>