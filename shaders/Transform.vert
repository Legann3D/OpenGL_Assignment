#version 330

in vec3 vPos;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

out vec4 fragPosition;

void main() {
    vec4 transformedPosition = projection * view * model * vec4(vPos, 1.0);
    gl_Position = transformedPosition;
    fragPosition = transformedPosition;
}