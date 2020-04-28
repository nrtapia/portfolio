Vue.component('timeline-item', {
  props: ['item'],
  template: `
      <li class="media my-4">
        <img v-bind:src="item.photo" class="mr-3">
        <div class="media-body">
          <h5 class="mt-0 mb-1">{{ item.name }}</h5>
          {{ item.description }}
        </div>
      </li>
    `
});