<template>
  <div>
    <img :src="oneroom.image" class="room-img" />
    <h4 @click="$emit('openModal', oneroom.id)">
      {{ oneroom.title }}
    </h4>
    <p>{{ oneroom.price }} 원</p>
  </div>
</template>

<script>
export default {
  props: {
    oneroom: Object,
    i: Number,
  },
};
</script>

<style>
.room-img{
  width: 500x;
  height: 300px;
  margin-top: 40px;
}

</style>
