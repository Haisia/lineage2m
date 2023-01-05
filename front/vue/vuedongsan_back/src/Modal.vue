<template>
  <div class="black-bg" v-if="modalOpen == true">
    <div class="white-bg">
      <img class="modalImg" :src="oneroom.image" />
      <h4>{{ oneroom.title }}</h4>
      <p>{{ oneroom.content }}</p>
      <p>{{ oneroom.price }}</p>
      <br />
      <button @click="$emit('closeModal')">닫기</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    oneroom: Object,
    modalOpen: Boolean,
  },
  methods: {},
};
</script>

<style></style>
