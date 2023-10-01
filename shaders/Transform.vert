#version 330

layout(location=0) in vec3 pos;
layout(location=1) in vec3 normal;
layout(location=2) in vec3 colour;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

out vec4 fragColour;
out vec4 transformedPosition;  
out vec3 fragNormal;

void main() {
    gl_Position = projection * view * model * vec4(pos, 1.0);
    transformedPosition = gl_Position;
    fragColour = vec4(colour, 1.0);
    fragNormal = mat3(transpose(inverse(view * model))) * normal;
}