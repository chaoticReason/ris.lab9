function getIndex(list, id) {
    for(var i = 0; i < list.length; i++){
        if(list[i].id === id){
            return i;
        }
    }
    return -1;
}
function getIndexOfCountry(list, name) {
    for(var i = 0; i < list.length; i++){
        if(list[i].name === name){
            return i;
        }
    }
    return -1;
}

var clientApi = Vue.resource("rest/users{/id}");

Vue.component('client-form', {
    props:['clients', 'clientAttr'],
    data: function(){
        return {
            firstname: '',
            lastname: '',
            patronym:'',
            email: '',
            id:'',
            birthDate: '',
            phoneNumber:'',
            address:'',
            country:'',
            citizenship:[],
            calendarConfig: {
                type: 'string',
                mask: 'YYYY-MM-DD'
            }
        }
    },
    watch: {
        clientAttr: function(newVal, oldVal){
            this.firstname = newVal.firstname;
            this.lastname = newVal.lastname;
            this.email = newVal.email;
            this.id = newVal.id;
            this.birthDate = newVal.birthDate;
            this.phoneNumber = newVal.phoneNumber;
            this.address = newVal.address;
            this.citizenship = newVal.citizenship.slice();
        }
    },
    template:
        '<form class="row my-5"> ' +
        '<div class="col-4">' +
            '<div class="mb-3">' +
                '<label for="i1" class="form-label">Firstname</label>' +
                '<input id="i1" class="form-control form-control-sm" type="text" v-model="firstname"/> ' +
            '</div>' +
            '<div class="mb-3">' +
                '<label for="i2" class="form-label">Lastname</label>' +
                '<input id="i2" class="form-control form-control-sm" type="text" v-model="lastname"/>' +
            '</div>' +
            '<div class="mb-3">' +
                '<label for="i3" class="form-label">Email</label>' +
                '<input id="i3" class="form-control form-control-sm" type="text" v-model="email"/>' +
            '</div>' +
            '<button type="button" class="btn btn-primary" @click="save">Save</button>' +
        '</div>' +
        '<div class="col-4">' +
            '<div class="mb-3 col">' +
                '<div>' +
                    '<label for="i4" class="form-label">Birth date</label>' +
                '</div>' +
                '<div>' +
                    '<v-date-picker v-model="birthDate" :model-config="calendarConfig">' +
                    '<template v-slot="{ inputValue, inputEvents }">' +
                    '<input id="i4" class="bg-white border px-2 py-1 rounded" :value="inputValue" v-on="inputEvents" readonly/>'+
                    '</template>'+
                    '</v-date-picker>'+
                '</div>' +
            '</div>' +
            '<div class="mb-3">' +
                '<label for="i5" class="form-label">Phone number</label>' +
                '<input id="i5" class="form-control form-control-sm" type="text" v-model="phoneNumber"/>' +
            '</div>' +
            '<div class="mb-3">' +
                '<label for="i6" class="form-label">Address</label>' +
                '<input id="i6" class="form-control form-control-sm" type="text" v-model="address"/>' +
            '</div>' +
        '</div>' +
        '<div class="col-4">' +
            '<label for="i7" class="form-label">Citizenship</label>' +
            '<div class="input-group mb-3"\>' +
                '<input id="i7" type="text" class="form-control form-control-sm" v-model="country">' +
                '<div class="input-group-append">\n' +
                '   <button class="btn btn-outline-primary btn-sm" type="button" @click="addCountry">Add</button>' +
                '</div>\n' +
            '</div>' +
            '<div class="d-flex align-content-start flex-wrap">\n' +
                '<div class="mx-1" v-for="c in citizenship">' +
                    '<div class="input-group mb-3">\n' +
                        '<span class="btn nohover btn-outline-secondary ">{{c.name}}</span>' +
                        '<button class="btn btn-outline-secondary" type="button" @click="delCountry(c.name)">x</button>\n' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>' +
        '</form>',
    methods: {
        save: function() {
            var client = {
                firstname: this.firstname,
                lastname: this.lastname,
                email: this.email,
                id: this.id,
                birthDate: this.birthDate,
                phoneNumber: this.phoneNumber,
                address:this.address,
                citizenship:this.citizenship
            };
            if(this.id){
                clientApi.update({id: this.id}, client).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.clients, this.id);
                        this.clients.splice(index, 1, data);
                        this.firstname = '';
                        this.lastname = '';
                        this.email = '';
                        this.id = '';
                        this.birthDate='';
                        this.address='';
                        this.phoneNumber='';
                        this.citizenship = [];
                        this.country = '';
                    })
                )
            }
            else{
                clientApi.save({}, client).then(result =>
                    result.json().then(data => {
                        this.clients.push(data);
                        this.firstname = '';
                        this.lastname = '';
                        this.email = '';
                        this.id = '';
                        this.birthDate = '';
                        this.phoneNumber = '';
                        this.address = '';
                        this.citizenship = [];
                        this.country = '';
                    })
                );
            }
        },
        addCountry: function(){
            this.citizenship.push({id:'',name: this.country});
            this.country = '';
        },
        delCountry: function(name){
            this.citizenship.splice(getIndexOfCountry(this.citizenship, name),1);
        }
    }
});

Vue.component('client-row', {
    props: ['client', 'editMethod', 'clients', 'citizenship'],
    data: function(){
        return {
            citizenship: client.citizenship
        }
    },
    template: '<tr>' +
        '          <td>{{client.id}}</td>\n' +
        '          <td>{{client.firstname}}</td>\n' +
        '          <td>{{client.lastname}}</td>\n' +
        '          <td>{{client.email}}</td>\n' +
        '          <td>{{client.birthDate}}</td>\n' +
        '          <td>{{client.phoneNumber}}</td>\n' +
        '          <td>{{client.address}}</td>\n' +
        '          <td>' +
        '               <span v-for="country in citizenship">\n' +
        '                   {{ country.name }}\n' +
        '               </span>' +
        '          </td>\n' +
        '          <td>' +
        '               <div class="btn-group" role="group" aria-label="Basic outlined example">' +
        '               <button type="button" class="btn btn-outline-primary btn-sm" @click="edit">Edit</button>' +
        '               <button type="button" class="btn btn-outline-primary btn-sm" @click="del">X</button>' +
        '               </div>' +
        '          </td>\n' +
        '      </tr>',
    methods: {
        edit: function(){
            this.editMethod(this.client);
        },
        del: function(){
            clientApi.remove({id: this.client.id}).then(result =>{
                if(result.ok){
                    this.clients.splice(this.clients.indexOf(this.client), 1)
                }
            })
        }
    }
    }
);

Vue.component('client-list', {
    props: ['clients'],
    data: function(){
        return{
            client: null
        }
    },
    template: '<div> ' +
        '<client-form :clients="clients" :clientAttr="client"/>' +
        '<div class="table-responsive">' +
        '<table class="table">' +
            '<thead> ' +
                '<tr>' +
                    '<th>id</th>' +
                    '<th>firstname</th>' +
                    '<th>lastname</th>' +
                    '<th>email</th>' +
                    '<th>birth date</th>' +
                    '<th>phone number</th>' +
                    '<th>address</th>' +
                    '<th>citizenship</th>' +
                    '<th></th>' +
                '</tr> ' +
            '</thead>' +
            '<tbody><client-row v-for="client in clients" :key="client.id" ' +
                ':client="client" :citizenship="client.citizenship" :editMethod="editMethod" :clients="clients"/></tbody>' +
        '</table>' +
        '</div>' +
        '</div>',
    created: function(){
        clientApi.get().then(result =>
            result.json().then(data =>
                data.forEach(client => this.clients.push(client))
            )
        )
    },
    methods: {
        editMethod: function(client){
            this.client = client;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<client-list :clients="clients"/>',
    data: {
        clients: []
    }
});