Vue.component('portfolio-photo', {
  props: ['image'],
  template: `
      <div>
        <img v-bind:src="image" class="img-fluid" alt="Profile Photo">
      </div>
    `
});