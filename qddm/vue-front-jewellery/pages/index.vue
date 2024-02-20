<template>
  
  <div>
    <!-- 幻灯片 开始 -->
<div v-swiper:mySwiper="swiperOption">
    <div class="swiper-wrapper">
        <div v-for="banner in bannerList" :key="banner.id" class="swiper-slide" style="background: #040B1B;">
            <a target="_blank" :href="banner.linkUrl">
              <img width="100%"
                :src="banner.imageUrl"
                :alt="banner.title"/>
            </a>
        </div>
 
    </div>
    <div class="swiper-pagination swiper-pagination-white"></div>
    <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
    <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
</div>
<!-- 幻灯片 结束 -->
    
     <div id="aCoursesList">
      <!-- 珠宝开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门珠宝</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="(jewellery, index) in jewelleryList" v-bind:key="index">
				<div class="cc-l-wrap">
					<section class="course-img">
<!-- ~/assets/photo/course/01.jpg -->
                        <img
                            :src="jewellery.cover"
                            class="img-responsive"
                            :alt="jewellery.title">
        <div class="cc-mask">
            <a :href="'/jewellery/'+jewellery.id" title="开始学习" class="comm-btn c-btn-1">珠宝详情</a>
            </div>
        </section>
                    <h3 class="hLh30 txtOf mt10">
                <a href="#" :title="jewellery.title" class="course-title fsize18 c-333">{{jewellery.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green" v-if="Number(jewellery.price) === 0">
                    <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fr jgTag bg-green" v-else>
                    <i class="c-fff fsize12 f-fA"> ￥{{jewellery.price}}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                    <i class="c-999 f-fA">{{jewellery.buyCount}} 人购买</i>
                                            |
                    <i class="c-999 f-fA">{{jewellery.viewCount}} 人浏览</i>
                    </span>
                    </section>
                    </div>
                    </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/jewellery" title="全部珠宝" class="comm-btn c-btn-2">全部珠宝</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /珠宝 结束 -->
      <!-- 设计师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">设计师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
               <li v-for="(designer,index) in designerList" v-bind:key="index">
                <section class="i-teach-wrap">
                <div class="i-teach-pic">
                <a :href='"/designer/"+designer.id' :title="designer.name">
                <img :alt="designer.name" :src="designer.avatar">
                </a>
                </div>
                <div class="mt10 hLh30 txtOf tac">
                <a :href='"/designer/"+designer.id' :title="designer.name" class="fsize18 c-666">{{designer.name}}</a>
                </div>
                <div class="hLh30 txtOf tac">
                <span class="fsize14 c-999">{{designer.intro}}</span>
                </div>
                <div class="mt15 i-q-txt">
                <p
                                        class="c-999 f-fA"
                >{{designer.career}}</p>
                </div>
                </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/designer" title="全部设计师" class="comm-btn c-btn-2">全部设计师</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /设计师 结束 -->
    </div>
  </div>
</template>

<script>
import index from '@/api/index'
import banner from "@/api/banner"

export default {
  data () {
    return {
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      },
      designerList: [],
      jewelleryList: [],
      bannerList: []

    }
  },
  created() {
    this.initDataBanner()
    this.initDataObj()
  },
  methods:{
    initDataBanner() {
      banner.getListBanner().then(response => {
        this.bannerList = response.data.data.bannerList
      })
    },

    initDataObj() {
      index.getIndexData().then(response => {
        this.designerList = response.data.data.designerList
        this.jewelleryList = response.data.data.jewelleryList
      })
    }
  }
}
</script>