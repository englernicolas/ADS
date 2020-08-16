export default {
    name: 'EditProfile',
    data: () => ({
        valid: true,
        name: '',
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        email: '',
        emailRules: [
            v => !!v || 'E-mail is required',
            v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
        password: '',
        select: 'Indefinido',
        genders: [
            'Indefinido',
            'Masculino',
            'Feminino',
        ],
        checkbox: false,
    }),

    methods: {
        validate () {
            this.$refs.form.validate()
        },
        reset () {
            this.$refs.form.reset()
        },
        resetValidation () {
            this.$refs.form.resetValidation()
        },
    },
    template: `<div>
                    <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Editar Perfil</span></div>
                                        
                    <v-divider></v-divider>
                    
                    <v-form class="mx-16 my-5" ref="form" v-model="valid">
                        <v-container>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        v-model="name" :rules="requiredMessage" label="Sobrenome" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="email" :rules="emailRules" label="E-mail" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                                <v-col>    
                                    <v-text-field 
                                        v-model="password" :rules="requiredMessage" label="Senha" color="teal" type="password" outlined required
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>    
                                    <v-select
                                        v-model="select" :items="genders" :rules="[v => !!v || 'Item is required']" label="Sexo" color="teal" required outlined
                                    ></v-select>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        v-model="name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row>     
                            
                            <v-row>
                                <v-col cols="2">    
                                    <v-btn :disabled="!valid" color="primary" class="mr-4 white--text text-lg-right" @click="validate">
                                        Salvar
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-form>
                    
               </div>`
}
