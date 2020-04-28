Vue.component('timeline', {
  props: ['portfolio'],
  template: `
      <div>
        <div class="card">
          <div class="card-header">
            <h4>{{ portfolio.title }}'s Timeline</h4>
          </div>
          <div class="card-body">
            <ul class="list-unstyled">
              <timeline-item v-for="item in portfolio.timeline" :key="item.id"
                             v-bind:item="item" v-bind:key="item.id">
              </timeline-item>
            </ul>
          </div>
          <div class="card-footer">
            <a href="#">Go to Account ></a>
          </div>
        </div>
      </div>
    `
});