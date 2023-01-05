<template>
  <div class="black-bg" v-if="modalOpen == true">
    <div class="white-bg">
      <img class="modalImg" :src="oneroom.image" />
      <h4>{{ oneroom.title }}</h4>
      <p>{{ oneroom.content }}</p>
      <input v-model.number="month">
      <p> {{month}}개월 선택함 : {{ oneroom.price * month }}</p>
      <br />
      <button @click="$emit('closeModal')">닫기</button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      month : 1,
    }
  },
  watch : {
    month(input){
      if(input>=13){
        alert('13이상 입력하지 마셈');
        this.month=1;
      }
      if( typeof(input) != 'number'){
        alert('숫자만 입력하셈');
        this.month=1;
      }
    },
  },

  props: {
    oneroom: Object,
    modalOpen: Boolean,
    i: Number,
  },
  methods: {

  },
};
</script>

<style>
</style>
