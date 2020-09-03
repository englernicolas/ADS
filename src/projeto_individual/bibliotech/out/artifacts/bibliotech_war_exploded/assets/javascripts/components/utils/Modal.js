import { $bus } from '../../utils/eventBus.js'

export default {
  props: {
    title: { type: String, default: '', required: true },
    maxWidth: { type: [String, Number], default: '500' },
    maxHeight: { type: [String, Number], default: '450' },
  },
  data() {
    return {
      showDialog: false,
    }
  },
  computed: {
    modalHeaderStyle() {
      return `
        border-bottom-left-radius: 0 !important;
        border-bottom-right-radius: 0 !important;
        overflow: hidden;
      `
    },
    
    modalBodyStyle() {
      return `
        border-top-left-radius: 0 !important;
        border-top-right-radius: 0 !important;
        overflow: hidden;
      `
    },
  },
  methods: {
    closeModal() {
      this.showDialog = false

      this.$router.push({ path: this.$route.path, query: {} })

      $bus.$emit('reset-modal-content')
    }
  },
  mounted() {
    $bus.$on('open-modal', () => {
      this.showDialog = true
    })
    
    $bus.$on('close-modal', () => {
      this.showDialog = false
    })
  },
  beforeDestroy(){
    $bus.$off('open-modal')
    $bus.$off('close-modal')
  },
  template: /*html*/`
    <v-dialog v-else v-model="showDialog" :max-width="maxWidth" :max-height="maxHeight" persistent>
      <v-card :style="modalHeaderStyle" color="primary">
        <v-row dense align="center" class="pl-3 pr-2 py-2">
          <v-col cols="10">
            <span class="text-h5 white--text ml-5 font-weight-bold">{{title}}</span>
          </v-col>
          
          <v-col cols="2" class="text-right">
            <v-btn icon large @click="closeModal" class="white--text">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-card>
      
      <v-card class="r-modal-card" :style="modalBodyStyle">
        <v-container>
          <slot />
        </v-container>
      </v-card>
    </v-dialog>`
}