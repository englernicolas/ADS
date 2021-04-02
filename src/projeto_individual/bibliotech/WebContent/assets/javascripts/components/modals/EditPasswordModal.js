import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'EditPasswordModal',
    data: () => ({
        valid: false,
        content: {
            oldPassword: '',
            newPassword: '',
        },
        confirmNewPassword: '',
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        loading: false,
        error: ""
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
        async changePassword () {
            this.error = ""
            this.validate()

            if (this.valid) {
                this.loading = true

                this.content.userId = auth.user.id
                this.content.oldPassword = btoa(this.content.oldPassword)
                this.content.newPassword = btoa(this.content.newPassword)
                
                const content = this.content

                await axios.put('/student/changePassword', content)
                    .then(() => {
                        window.location.reload()
                    })
                    .catch(() => {
                        this.content = {}
                        this.confirmNewPassword = ""
                        this.error = "Ocorreu um erro ao tentar mudar a senha"
                    })
                    .finally(() => {
                        this.loading = false
                    })
            }
        }
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row v-if="this.error!=''">
                    <span class="mx-auto red--text">{{error}}</span>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="content.oldPassword" type="password" :rules="requiredMessage" label="Senha atual" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="content.newPassword" type="password" :rules="requiredMessage" label="Nova senha" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>   
                <span class="red--text" v-if="content.newPassword != confirmNewPassword">As senhas não conferem</span> 
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="confirmNewPassword" type="password" :rules="requiredMessage" label="Confirmar nova senha" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row> 
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid || loading == true || content.newPassword != confirmNewPassword" color="primary" class="white--text text-lg-right" @click="changePassword">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
