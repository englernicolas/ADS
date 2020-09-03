import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'DeleteLibrarianModal',
    data: () => ({
        valid: false,
        reason: '',
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.$refs.form.reset()
            })   
        }
    },
    beforeDestoy() {
        $bus.$off('reset-modal-content')
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-textarea
                            v-model="reason" :rules="requiredMessage" color="teal" placeholder="Seu motivo aqui" required outlined
                        ></v-textarea>
                    </v-col>
                </v-row> 
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="error" class="white--text text-lg-right" @click="validate">
                            Deletar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
